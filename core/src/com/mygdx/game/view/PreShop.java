package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.utils.TextManager;

public class PreShop implements Screen {
    StageForButton Guns,Ships;
    Button.ButtonStyle Gstyle,Sstyle;
    Skin skin;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    Game game;
    Screen ShList;
    OrthographicCamera camera;
    TextManager textManager;
    public PreShop(Game game,SpriteBatch batch, TextureAtlas textureAtlas){
        this.game=game;
        this.batch = batch;
        this.textureAtlas=textureAtlas;



    }

    @Override
    public void show() {
        textManager=new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin=new Skin();
        batch=new SpriteBatch();

        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        ShList=new ShopList(game,batch,textureAtlas);
        //creating button with guns image
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");
        Guns=new StageForButton(Gstyle,0,Gdx.graphics.getHeight()-51);
        //change scene when 'guns' clicked
        Guns.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

              //  game.setScreen();

            }
        });



        //creating button with ships image
        Sstyle = new Button.ButtonStyle();
        Sstyle.up = skin.getDrawable("UnSelectShip");
        Sstyle.down = skin.getDrawable("UnSelectShip");
        Ships=new StageForButton(Sstyle,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-51);
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(ShList);

            }
        });
        //InputMultiplexer in=new InputMultiplexer();

       // in.addProcessor(Ships);


        Gdx.input.setInputProcessor(Ships);
    }

    @Override
    public void render(float delta) {
       camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        textManager.displayMessage(batch,"Guns " ,Color.BLACK,40, (int) (Gdx.graphics.getWidth()/4.6), Gdx.graphics.getHeight()-60);
        textManager.displayMessage(batch,"Ships" ,Color.BLACK,40, (Gdx.graphics.getWidth())-(int) (Gdx.graphics.getWidth()/3.4), Gdx.graphics.getHeight()-60);
        Guns.act(delta);
        Guns.draw();
        Ships.act(delta);
        Ships.draw();
        batch.begin();
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Guns.dispose();
        Ships.dispose();
        batch.dispose();
        game.dispose();
        textureAtlas.dispose();


    }
    class StageForButton extends Stage {
        Button btn;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y) {

            btn = new Button(btnstyle);
            btn.setBounds(x, y, Gdx.graphics.getWidth()/2, 50);




            addActor(btn);
        }
    }
}
