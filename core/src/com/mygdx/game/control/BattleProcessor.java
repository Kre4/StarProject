package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.model.Ship;
import com.mygdx.game.utils.Joystick;
import com.mygdx.game.view.Battle;

import static com.mygdx.game.view.Battle.heightCamera;

/**
 * Created by Sash on 01.05.2018.
 */

public class BattleProcessor implements InputProcessor {
    Joystick joystick;
    Ship ship;
    float convX= Gdx.graphics.getWidth()/ Battle.widthCamera;
    float convY=Gdx.graphics.getHeight()/ heightCamera;

    public static float offsetX;//переменные определяющие смещение статичной части относительно позиции камеры
    public static float offsetY;
    public static float offsetDynamicX;//переменные определяющие смещение динамической части относительно статичной
    public static float offsetDynamicY;

    int joystickPointer;
    int turnPointer;
    public BattleProcessor(Joystick joystick,Ship ship)
    {
        this.joystick=joystick;
        this.ship=ship;


    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX<Gdx.graphics.getWidth()*0.6) {
            joystick.create(screenX / convX + (Battle.camX - Battle.widthCamera / 2), heightCamera - screenY / convY + (Battle.camY - heightCamera / 2));//конвертация координат получаемых с InputProcessor в координаты экрана
            offsetX = joystick.staticPart.getCenterX() - (Battle.camX - Battle.widthCamera / 2);
            offsetY = joystick.staticPart.getCenterY() - (Battle.camY - heightCamera / 2);
            joystickPointer=pointer;
        }
        if((screenX / convX + (Battle.camX - Battle.widthCamera / 2)>=Battle.camX+Battle.widthCamera/5)&&((screenX / convX + (Battle.camX - Battle.widthCamera / 2)<=Battle.camX+Battle.widthCamera/5+20)))//1-я кнопка x
        {

            if((heightCamera - screenY / convY + (Battle.camY - heightCamera / 2)>=Battle.camY-heightCamera/3)&&(heightCamera - screenY / convY + (Battle.camY - heightCamera / 2)<=Battle.camY-heightCamera/3+20))//1-я кнопка y
            {
               ship.setRotationDirection(-1);
               turnPointer=pointer;
            }

        }
        if((screenX / convX + (Battle.camX - Battle.widthCamera / 2)>=Battle.camX+Battle.widthCamera/5+30)&&((screenX / convX + (Battle.camX - Battle.widthCamera / 2)<=Battle.camX+Battle.widthCamera/5+20+30)))//1-я кнопка x
        {

            if((heightCamera - screenY / convY + (Battle.camY - heightCamera / 2)>=Battle.camY-heightCamera/3)&&(heightCamera - screenY / convY + (Battle.camY - heightCamera / 2)<=Battle.camY-heightCamera/3+20))//1-я кнопка y
            {
                ship.setRotationDirection(1);
                turnPointer=pointer;
            }

        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer==joystickPointer) {
            joystick.setActive(false);
            joystickPointer=0;
        }
        if(pointer==turnPointer) {
            ship.setRotationDirection(0);
            turnPointer=0;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //joystick.staticPart.setCenter((Battle.camX-Battle.widthCamera/2)+offsetX,(Battle.camY-Battle.heightCamera/2)+offsetY);
        if(pointer==joystickPointer) {
            joystick.dynamicPart.setCenter(screenX / convX + (Battle.camX - Battle.widthCamera / 2), heightCamera - screenY / convY + (Battle.camY - heightCamera / 2));//смещение динамической части относительно статичной
            offsetDynamicX = joystick.dynamicPart.getCenterX() - joystick.staticPart.getCenterX();
            offsetDynamicY = joystick.dynamicPart.getCenterY() - joystick.staticPart.getCenterY();
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
