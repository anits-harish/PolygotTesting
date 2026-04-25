import pytest
import logging
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options

logger = logging.getLogger("PythonTestLogger")

def test_google_search_title():
    """
    Sample Web Automation test using Selenium in Python.
    Navigates to Google and asserts the title.
    """
    logger.info("Executing Python Pytest: test_google_search_title (Web Automation)")
    
    options = Options()
    options.add_argument('--headless')
    options.add_argument('--no-sandbox')
    options.add_argument('--disable-dev-shm-usage')
    
    # Init driver natively
    driver = webdriver.Chrome(options=options)
    
    try:
        driver.get("https://www.google.com")
        logger.info(f"Navigated to: {driver.current_url}")
        
        assert "Google" in driver.title
        logger.info("Successfully asserted Google page title.")
        
    finally:
        driver.quit()
