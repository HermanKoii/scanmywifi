from __future__ import annotations
from typing import Optional, Dict, Any
from dataclasses import dataclass, field
from enum import Enum, auto
import logging

class IterationStatus(Enum):
    """Enum representing the status of an iteration."""
    INITIALIZED = auto()
    IN_PROGRESS = auto()
    COMPLETED = auto()
    FAILED = auto()
    TERMINATED = auto()

@dataclass
class IterationState:
    """Tracks the state and metadata of learning iterations."""
    current_iteration: int = 0
    max_iterations: Optional[int] = None
    status: IterationStatus = IterationStatus.INITIALIZED
    metadata: Dict[str, Any] = field(default_factory=dict)
    
    def increment(self) -> None:
        """
        Increment the iteration count and update status.
        
        Raises:
            StopIteration: If maximum iterations are reached.
        """
        self.current_iteration += 1
        
        if self.max_iterations is not None and self.current_iteration >= self.max_iterations:
            self.status = IterationStatus.COMPLETED
            raise StopIteration("Maximum iterations reached")
        
        self.status = IterationStatus.IN_PROGRESS
    
    def reset(self) -> None:
        """Reset the iteration state to initial conditions."""
        self.current_iteration = 0
        self.status = IterationStatus.INITIALIZED
        self.metadata.clear()
    
    def set_status(self, status: IterationStatus) -> None:
        """
        Set the current iteration status.
        
        Args:
            status (IterationStatus): New status to set
        """
        self.status = status
    
    def add_metadata(self, key: str, value: Any) -> None:
        """
        Add metadata to the current iteration.
        
        Args:
            key (str): Metadata key
            value (Any): Metadata value
        """
        self.metadata[key] = value
    
    @property
    def is_complete(self) -> bool:
        """
        Check if iteration is complete.
        
        Returns:
            bool: True if iteration is completed, False otherwise
        """
        return self.status in {IterationStatus.COMPLETED, IterationStatus.TERMINATED, IterationStatus.FAILED}

class IterationTracker:
    """
    Manages the iteration tracking process for the Adaptive Learning Process.
    
    Provides comprehensive tracking of iterations with configurable maximum 
    iterations and detailed state management.
    """
    
    def __init__(self, max_iterations: Optional[int] = None, logger: Optional[logging.Logger] = None):
        """
        Initialize the iteration tracker.
        
        Args:
            max_iterations (Optional[int], optional): Maximum number of iterations. Defaults to None.
            logger (Optional[logging.Logger], optional): Logger instance. Defaults to None.
        """
        self._state = IterationState(max_iterations=max_iterations)
        self._logger = logger or logging.getLogger(__name__)
    
    def start(self) -> None:
        """Start the iteration tracking process."""
        self._state.reset()
        self._logger.info("Iteration tracking started")
    
    def next_iteration(self) -> int:
        """
        Move to the next iteration.
        
        Returns:
            int: Current iteration number
        
        Raises:
            StopIteration: If maximum iterations are reached
        """
        try:
            self._state.increment()
            self._logger.info(f"Starting iteration {self._state.current_iteration}")
            return self._state.current_iteration
        except StopIteration:
            self._logger.info("Maximum iterations reached")
            raise
    
    def mark_iteration_complete(self, metadata: Optional[Dict[str, Any]] = None) -> None:
        """
        Mark the current iteration as complete.
        
        Args:
            metadata (Optional[Dict[str, Any]], optional): Additional metadata about the iteration. Defaults to None.
        """
        if metadata:
            for key, value in metadata.items():
                self._state.add_metadata(key, value)
        
        self._state.set_status(IterationStatus.COMPLETED)
        self._logger.info(f"Iteration {self._state.current_iteration} completed")
    
    def mark_iteration_failed(self, error: Optional[Exception] = None) -> None:
        """
        Mark the current iteration as failed.
        
        Args:
            error (Optional[Exception], optional): Error that caused the failure. Defaults to None.
        """
        self._state.set_status(IterationStatus.FAILED)
        
        if error:
            self._logger.error(f"Iteration {self._state.current_iteration} failed: {error}")
            self._state.add_metadata('error', str(error))
    
    def terminate(self, reason: Optional[str] = None) -> None:
        """
        Terminate the iteration process.
        
        Args:
            reason (Optional[str], optional): Reason for termination. Defaults to None.
        """
        self._state.set_status(IterationStatus.TERMINATED)
        
        if reason:
            self._logger.warning(f"Iteration process terminated: {reason}")
            self._state.add_metadata('termination_reason', reason)
    
    @property
    def current_iteration(self) -> int:
        """
        Get the current iteration number.
        
        Returns:
            int: Current iteration number
        """
        return self._state.current_iteration
    
    @property
    def is_complete(self) -> bool:
        """
        Check if iteration process is complete.
        
        Returns:
            bool: True if iterations are complete, False otherwise
        """
        return self._state.is_complete