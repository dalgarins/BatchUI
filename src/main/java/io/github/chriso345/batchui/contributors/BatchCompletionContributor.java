package io.github.chriso345.batchui.contributors;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import io.github.chriso345.batchui.psi.BatchTypes;
import io.github.chriso345.batchui.settings.BatchSettingsState;
import io.github.chriso345.batchui.util.BatchCommand;
import io.github.chriso345.batchui.BatchIcons;
import org.jetbrains.annotations.NotNull;

public class BatchCompletionContributor extends CompletionContributor {
    BatchCompletionContributor() {
        // Annotations
        extend(CompletionType.BASIC, PlatformPatterns.or(PlatformPatterns.psiElement(BatchTypes.DECORATOR), PlatformPatterns.psiElement(BatchTypes.PLAINTEXT)),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
                        BatchSettingsState settings = BatchSettingsState.getInstance();
                        boolean isUpperCase = settings.upperCaseKeywords;
                        for (BatchCommand command : BatchCommand.values()) {
                            String commandString = command.getCommand(isUpperCase);

                            if (settings.originalCommands.contains(commandString.toLowerCase()))
                                commandString = settings.overrideCommands.get(settings.originalCommands.indexOf(commandString.toLowerCase()));


                            resultSet.addElement(LookupElementBuilder.create(commandString)
                                    .withIcon(BatchIcons.COMMAND));
                        }
                    }
                }
        );

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(BatchTypes.FUNC_LABEL),
            new CompletionProvider<>() {
                public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
                    resultSet.addElement(LookupElementBuilder.create("eof")
                            .withIcon(BatchIcons.FILE));
                }
            });

        // Toggles
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(BatchTypes.PLAINTEXT),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("on")
                                .withIcon(BatchIcons.FILE));
                        resultSet.addElement(LookupElementBuilder.create("off")
                                .withIcon(BatchIcons.FILE));
                    }
                }
        );

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(BatchTypes.SETLOCAL_PARAMETER),
            new CompletionProvider<>() {
                public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
                    resultSet.addElement(LookupElementBuilder.create("enableExtensions"));
                    resultSet.addElement(LookupElementBuilder.create("disableExtensions"));
                    resultSet.addElement(LookupElementBuilder.create("enableDelayedExpansion"));
                    resultSet.addElement(LookupElementBuilder.create("disableDelayedExpansion"));
                }
            }
        );
    }
}
