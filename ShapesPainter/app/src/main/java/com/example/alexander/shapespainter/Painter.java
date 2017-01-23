package com.example.alexander.shapespainter;


import com.example.alexander.shapespainter.model.Shape;

class Painter implements IPainter {
    @Override
    public void drawPicture(PictureDraft draft, ICanvas canvas) {
        for(Shape shape : draft.getShapes()){
            shape.draw(canvas);
        }
    }
}
