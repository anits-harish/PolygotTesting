import pytest
import logging
import sys

# Configure a robust Python logger
logger = logging.getLogger("PythonTestLogger")
logger.setLevel(logging.INFO)

# File handler
file_handler = logging.FileHandler("python-execution.log", mode='a')
formatter = logging.Formatter('[%(asctime)s] %(message)s', datefmt='%T')
file_handler.setFormatter(formatter)

# Console handler (ensures it still prints to terminal for Java to capture)
console_handler = logging.StreamHandler(sys.stdout)
console_handler.setFormatter(formatter)

logger.addHandler(file_handler)
logger.addHandler(console_handler)

logger.info("===========================================")
logger.info(" Python Pytest Suite Initialized ")
logger.info("===========================================")

def test_addition():
    logger.info("Executing Python Pytest: test_addition")
    assert 2 + 2 == 4

def test_condition():
    logger.info("Executing Python Pytest: test_condition")
    assert 10 > 5
