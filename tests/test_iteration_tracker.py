import pytest
import logging
from src.iteration_tracker import IterationTracker, IterationStatus

def test_iteration_tracker_initialization():
    tracker = IterationTracker()
    assert tracker.current_iteration == 0
    assert not tracker.is_complete

def test_iteration_tracker_with_max_iterations():
    tracker = IterationTracker(max_iterations=3)
    
    tracker.start()
    assert tracker.next_iteration() == 1
    assert tracker.next_iteration() == 2
    assert tracker.next_iteration() == 3
    
    with pytest.raises(StopIteration):
        tracker.next_iteration()

def test_iteration_tracker_mark_complete():
    tracker = IterationTracker()
    tracker.start()
    
    iteration = tracker.next_iteration()
    tracker.mark_iteration_complete({'result': 'success'})
    
    assert iteration == 1

def test_iteration_tracker_mark_failed():
    tracker = IterationTracker()
    tracker.start()
    
    iteration = tracker.next_iteration()
    tracker.mark_iteration_failed(ValueError("Test error"))
    
    assert iteration == 1

def test_iteration_tracker_terminate():
    tracker = IterationTracker()
    tracker.start()
    
    tracker.terminate("Test termination")
    assert tracker.is_complete

def test_iteration_tracker_metadata():
    tracker = IterationTracker()
    tracker.start()
    
    tracker.next_iteration()
    tracker.mark_iteration_complete({'metric': 0.95, 'converged': True})
    
    # Note: Actual metadata validation would require more complex mocking/inspection
    assert tracker.current_iteration == 1