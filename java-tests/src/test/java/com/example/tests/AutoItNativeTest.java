package com.example.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// NOTE: To compile and run this class successfully, you need to add `autoitx4java.jar` 
// and `jacob.jar` to your project's build path, along with placing the `jacob.dll` 
// in your system's path. Since these are not available in standard Maven Central, 
// they must be downloaded manually into a `lib/` directory.

// import autoitx4java.AutoItX;
// import com.jacob.com.LibraryLoader;

public class AutoItNativeTest {

    @BeforeClass
    public void setupJacobBinding() {
        String os = System.getProperty("os.name").toLowerCase();
        if (!os.contains("win")) {
            throw new SkipException("AutoIt and Jacob DLLs explicitly require a Windows OS.");
        }
        
        /* 
        // Example DLL loading configuration for Windows runners:
        String jacobDllVersion = System.getProperty("sun.arch.data.model").contains("32") ? "jacob-1.18-x86.dll" : "jacob-1.18-x64.dll";
        File dllFile = new File("src/test/resources/lib/" + jacobDllVersion);
        if (dllFile.exists()) {
             System.setProperty(LibraryLoader.JACOB_DLL_PATH, dllFile.getAbsolutePath());
        } else {
             throw new RuntimeException("Jacob DLL not found at: " + dllFile.getAbsolutePath());
        }
        */
    }

    @Test
    public void testNotepadWithNativeJavaBindings() {
        TestLogger.log("Starting Notepad natively via Java AutoItX bindings...");
        
        /* 
        // NOTE: Uncomment these lines once `autoitx4java` is on your classpath!
        
        AutoItX autoIt = new AutoItX();
        
        // 1. Run Notepad
        autoIt.run("notepad.exe", "", AutoItX.SW_SHOW);
        
        // 2. Wait up to 10 seconds for it to become active
        // AutoItX matching defaults to partial matching if configured, 
        // but "[CLASS:Notepad]" is the safest window handle
        autoIt.winWaitActive("[CLASS:Notepad]", "", 10);
        
        // 3. Type characters into the active window
        autoIt.send("This is fully automated text sent directly from Java using AutoItX4Java!");
        
        // Sleep to visually verify
        autoIt.sleep(2000);
        
        // 4. Close Notepad
        autoIt.winClose("[CLASS:Notepad]");
        
        // 5. Handle the popup "Do you want to save changes?"
        autoIt.winWaitActive("Notepad", "Save", 5);
        autoIt.send("!n"); // Sends ALT+N (which clicks 'Don't Save')
        
        // 6. Ensure the window is fully destroyed
        autoIt.winWaitClose("[CLASS:Notepad]", "", 5);
        
        TestLogger.log("Notepad automation finished successfully!");
        */
    }
}
