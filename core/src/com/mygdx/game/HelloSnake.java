package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class HelloSnake extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture snakeImg;
	private Texture cherryImg;

	private Snake snake;
	private Cherry cherry;
	private boolean gameOver;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		snakeImg = new Texture("snake.png");
		cherryImg = new Texture("cherry.png");

		snake = new Snake(snakeImg);
		cherry = new Cherry(cherryImg);

		initializeNewGame();
	}

	private void initializeNewGame() {
		snake.initialize();
		cherry.randomizePosition();
		gameOver = false;
	}

	@Override
	public void render () {
		updateGame();

		ScreenUtils.clear(1, 1, 1, 1);

		batch.begin();

		cherry.draw(batch);
		snake.draw(batch);

		batch.end();
	}

	private void updateGame() {
		if (!gameOver) {
			snake.act(Gdx.graphics.getDeltaTime());

			if (snake.isCherryFound(cherry.getPosition())) {
				snake.extendSnake();
				cherry.randomizePosition();
			}

			if (snake.hasHitHimself()) {
				gameOver = true;
			}
		} else {
			if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
				initializeNewGame();
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		snakeImg.dispose();
		cherryImg.dispose();
	}
}
