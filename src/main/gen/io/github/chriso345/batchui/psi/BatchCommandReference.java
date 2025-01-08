// This is a generated file. Not intended for manual editing.
package io.github.chriso345.batchui.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BatchCommandReference extends PsiElement {

  @Nullable
  BatchCallArguments getCallArguments();

  @Nullable
  BatchCommand getCommand();

  @Nullable
  BatchEchoArguments getEchoArguments();

  @Nullable
  BatchExitArguments getExitArguments();

  @Nullable
  BatchLabel getLabel();

  @Nullable
  BatchSetArguments getSetArguments();

  @Nullable
  BatchSetlocalArguments getSetlocalArguments();

  @Nullable
  BatchShiftArguments getShiftArguments();

}
