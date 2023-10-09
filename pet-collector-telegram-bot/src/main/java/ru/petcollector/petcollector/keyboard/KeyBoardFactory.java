package ru.petcollector.petcollector.keyboard;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonRequestUser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import static ru.petcollector.petcollector.unils.Constans.NEW_DEBT_REQUEST_ID;
import static ru.petcollector.petcollector.unils.Constans.NEW_DEBT;

public class KeyBoardFactory {

    private KeyBoardFactory(){}

    public static ReplyKeyboardMarkup UserRequestKeyboard() {
        KeyboardButtonRequestUser requestUser = new KeyboardButtonRequestUser(NEW_DEBT_REQUEST_ID);
        KeyboardButton keyboardButton = new KeyboardButton(NEW_DEBT);
        keyboardButton.setRequestUser(requestUser);
        KeyboardRow row = new KeyboardRow(List.of(keyboardButton));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(row));
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

}
