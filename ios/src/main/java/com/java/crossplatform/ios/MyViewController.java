package com.java.crossplatform.ios;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.java.crossplatform.core.DataProvider;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.objc.annotation.IBAction;
import org.robovm.objc.annotation.IBOutlet;

import java.io.File;
import java.sql.SQLException;

@CustomClass("MyViewController")
public class MyViewController extends UIViewController {
    private UILabel label;
    private int clickCount;

    @IBOutlet
    public void setLabel(UILabel label) {
        this.label = label;
    }

    @IBAction
    private void clicked() {
        try {
            Class.forName("SQLite.JDBCDriver");
            File dbFile = new File(System.getenv("HOME"), "Documents/db.sqlite");
            dbFile.getParentFile().mkdirs();
            label.setText(DataProvider.getData(new JdbcConnectionSource("jdbc:sqlite:" + dbFile.getAbsolutePath())));
        } catch (SQLException e) {
            e.printStackTrace();
            label.setText("NOTHING");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            label.setText("DRIVER");
        }
    }
}
