package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Button {

    @NotNull
    private String name;

    @NotNull
    private String callback;

    public static Button valueOf(final String name, final String callback) {
        return new Button(name, callback);
    }

}
