package net.qiqb.ideaplug;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class SampleDialogWrapper extends DialogWrapper {

    private JTextField textField;

    public SampleDialogWrapper() {
        super(true); // use current window as parent
        setTitle("New Domain");
        init();
    }

    public String getText(){
        return this.textField.getText();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());
         textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 20));
        dialogPanel.add(textField,BorderLayout.CENTER);
        return dialogPanel;
    }
}