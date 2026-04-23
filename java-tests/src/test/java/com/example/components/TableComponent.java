package com.example.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TableComponent {
    private WebElement tableElement;

    public TableComponent(WebElement tableElement) {
        this.tableElement = tableElement;
    }

    public int getRowCount() {
        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        return rows.size();
    }

    // Add other table specific interactions (e.g., getCellData)
}
