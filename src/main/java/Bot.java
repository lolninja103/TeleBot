import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bot extends TelegramLongPollingBot {
    public long chat_id;

    @Override

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            chat_id = update.getMessage().getChatId();
            String s = update.getMessage().getText();
            System.out.println(s);
            Date date = new Date();
            if (s.equals("time")) {
                SendMessage massage = new SendMessage();
                massage.setChatId(String.valueOf(chat_id));
                massage.setText(date.toString());
                System.out.println(date);
                try {
                    execute(massage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

            if (s.equals("news")) {
                SendMessage massage = new SendMessage();
                massage.setChatId(String.valueOf(chat_id));
                String da = null;
                try {
                    da = Parsing.da();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                massage.setText(da);
                try {
                    execute(massage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chat_id));

                String messagetotal[] = s.split(" ");
                String g= null;
                try {
                    g = Time.function(messagetotal[0],messagetotal[1]).toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                message.setText(g);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


            }

        }


    }

    @Override
    public String getBotUsername() {
        return "DigitalNewTech_bot";
    }

    @Override
    public String getBotToken() {
        return "5888156059:AAEjbBdRU83K-cgqhVuwWbwlc6N1gAWyLNQ";
    }
}
