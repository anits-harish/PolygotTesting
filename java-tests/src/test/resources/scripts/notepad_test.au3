; Sample AutoIt wrapper for Desktop Application interaction (Notepad)

Run("notepad.exe")

; Wait for the Notepad window (max 10 sec)
WinWaitActive("[CLASS:Notepad]", "", 10)

; Type text at the cursor position
Send("Hello from AutoIt Script triggered by Java TestNG!")

; Sleep for 2 seconds to keep it visible
Sleep(2000)

; Close Notepad
WinClose("[CLASS:Notepad]")

; Wait for the save dialog and send Alt+N (Don't Save)
WinWaitActive("Notepad", "")
Send("!n")
