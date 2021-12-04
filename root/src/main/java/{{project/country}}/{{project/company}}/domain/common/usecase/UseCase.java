package ru.peter.domain.common.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class UseCase<Input extends UseCase.InputValues, Output extends UseCase.OutputValues> {

    public abstract Output execute(Input inputValues);

    public interface InputValues {
    }

    public interface OutputValues {
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class SingleInput<Value> implements InputValues {
        private final Value value;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class SingleOutput<Value> implements OutputValues {
        private final Value value;
    }

    @NoArgsConstructor(staticName = "empty")
    public static class VoidInput implements InputValues{

    }


    @NoArgsConstructor(staticName = "empty")
    public static class VoidOutput implements OutputValues{

    }
}
