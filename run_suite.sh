#!/bin/bash

# Multi-Language Test Suite Runner

echo "==========================================="
echo " Starting Multi-Language Test Suite"
echo "==========================================="

SUITE_PASSED=true

# --- 1. Python Pytest Execution ---
echo ""
echo ">>> Setting up Python environment..."
cd python-tests || exit 1

# Create virtual environment if it doesn't exist
if [ ! -d "venv" ]; then
    python3 -m venv venv
fi

# Activate virtual environment
source venv/bin/activate
pip install --quiet -r requirements.txt

echo ">>> Running Python Pytest..."
# Run pytest and capture exit code
pytest tests/ -v
PYTHON_EXIT_CODE=$?

if [ $PYTHON_EXIT_CODE -ne 0 ]; then
    echo ">>> Python tests FAILED!"
    SUITE_PASSED=false
else
    echo ">>> Python tests PASSED!"
fi

# Deactivate venv and return to root
deactivate
cd ..

# --- 2. Java TestNG Execution ---
echo ""
echo ">>> Running Java TestNG Tests..."
cd java-tests || exit 1

# Run maven tests and capture exit code
mvn test
JAVA_EXIT_CODE=$?

if [ $JAVA_EXIT_CODE -ne 0 ]; then
    echo ">>> Java tests FAILED!"
    SUITE_PASSED=false
else
    echo ">>> Java tests PASSED!"
fi

cd ..

# --- 3. Final Summary ---
echo ""
echo "==========================================="
echo " Test Suite Summary"
echo "==========================================="

if [ "$SUITE_PASSED" = true ]; then
    echo "STATUS: ALL TESTS PASSED SUCCESSFULLY"
    exit 0
else
    echo "STATUS: ONE OR MORE TEST SUITES FAILED"
    exit 1
fi
