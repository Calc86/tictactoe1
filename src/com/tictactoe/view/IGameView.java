package com.tictactoe.view;

import com.tictactoe.model.History;
import com.tictactoe.model.Player;

public interface IGameView {
    /**
     * Объявляем информацию о ходе игрока
     */
    void beforeTurn();

    /**
     * Вызывается поле получения данных о ходе
     */
    void onTurn();  //мы только что сделали ход, необходимо обновить view


    /**
     * Необходимо обновить поле
     */
    void afterTurn();

    /**
     * Начало игры (все окна должны быть созданы, объявляем о том, с кем мы играем
     */
    void onStart();

    /**
     * Показать историю
     * @param history
     */
    void showHistory(History history);

    /**
     * Конец игры, есть победитель
     * @param winner
     */
    void onWin(Player winner);

    /**
     * Конец игры, Ничья
     */
    void onDraw();

    /**
     * Сообщить о конце игры
     */
    void onEnd();

    /**
     * Сообщение об ошибке
     * @param string
     */
    void onError(String string);

    /**
     * Информационное сообщение/log
     * Классом Gamе не вызывается
     * @param string
     */
    void onMessage(String string);
}
