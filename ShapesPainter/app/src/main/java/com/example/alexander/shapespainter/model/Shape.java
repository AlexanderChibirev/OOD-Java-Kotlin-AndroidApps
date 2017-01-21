package com.example.alexander.shapespainter.model;


import com.example.alexander.shapespainter.ICanvas;

abstract public class Shape implements IShape {
    protected abstract void draw(ICanvas canvas);
}
