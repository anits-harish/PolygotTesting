import pytest
import sys
import time

try:
    import autoit
    HAS_AUTOIT = True
except AttributeError:
    # AutoIt relies on ctypes.windll which is only available on Windows.
    # When importing on macOS/Linux it throws AttributeError
    HAS_AUTOIT = False
except OSError:
    # Can also throw OSError if Windows DLLs are missing
    HAS_AUTOIT = False

@pytest.mark.skipif(not HAS_AUTOIT, reason="AutoIt only runs on Windows environments")
def test_notepad_automation():
    """
    Sample desktop automation test using PyAutoIt.
    This test will open Notepad, write text, and close it without saving.
    """
    # 1. Run Notepad
    autoit.run("notepad.exe")
    
    # 2. Wait for Notepad window to become active
    autoit.win_wait_active("[CLASS:Notepad]", 10)
    
    # 3. Type some text into the edit control
    autoit.control_send("[CLASS:Notepad]", "Edit1", "Hello from PyAutoIt Automation in Pytest!")
    
    # Wait for a moment to see the input
    time.sleep(2)
    
    # 4. Close Notepad
    autoit.win_close("[CLASS:Notepad]")
    
    # 5. Handle the "Do you want to save changes?" popup
    # Wait for the confirmation dialog
    autoit.win_wait_active("Notepad", 5) 
    
    # Send ALT+N to click "Don't Save"
    autoit.send("!n")

    # 6. Wait until notepad is completely closed
    autoit.win_wait_close("[CLASS:Notepad]", 5)
