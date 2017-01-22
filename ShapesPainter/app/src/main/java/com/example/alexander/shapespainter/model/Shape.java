package com.example.alexander.shapespainter.model;


import com.example.alexander.shapespainter.ICanvas;

abstract public class Shape implements IShape {
    public abstract void draw(ICanvas canvas);
}
