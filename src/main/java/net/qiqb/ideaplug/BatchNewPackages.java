package net.qiqb.ideaplug;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 批量生成java 包
 */
public class BatchNewPackages extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // 读取鼠标停留在类文件中的信息
        final Document document = EditorFactory.getInstance().createDocument("");
        final Project project = e.getData(PlatformDataKeys.PROJECT);
        final VirtualFile data = e.getData(PlatformDataKeys.PROJECT_FILE_DIRECTORY);
        final PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        final VirtualFile virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        final SampleDialogWrapper sampleDialogWrapper = new SampleDialogWrapper();
        if (sampleDialogWrapper.showAndGet()) {
            String domainName = sampleDialogWrapper.getText();
            // 在正确的线程中执行写操作
            Application application = ApplicationManager.getApplication();
            application.runWriteAction(() -> {
                try {
                    if (virtualFile != null) {
                        final VirtualFile childDirectory = virtualFile.createChildDirectory(this, domainName);
                        childDirectory.createChildDirectory(this, "adapter");
                        childDirectory.createChildDirectory(this, "application");
                        childDirectory.createChildDirectory(this, "domain");
                        childDirectory.createChildDirectory(this, "infrastructure");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        }


    }
}
