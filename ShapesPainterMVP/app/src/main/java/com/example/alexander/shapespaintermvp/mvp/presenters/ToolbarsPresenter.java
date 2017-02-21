package com.example.alexander.shapespaintermvp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeFactory;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.mvp.presenters.commands.AddShapeCommand;
import com.example.alexander.shapespaintermvp.mvp.views.ToolbarsView;

@InjectViewState
public class ToolbarsPresenter extends MvpPresenter<ToolbarsView> {
    private ShapeFactory mShapeFactory = new ShapeFactory();

    public void redo() {
        if (CommandStack.getInstance().redoEnabled()) {
            CommandStack.getInstance().redo();
            getViewState().redo();
        } else {
            getViewState().showMessage("вернуть нельзя");
        }
    }

    public void undo() {
        if (CommandStack.getInstance().undoEnabled()) {
            CommandStack.getInstance().undo();
            getViewState().undo();
        } else {
            getViewState().showMessage("отменить нельзя");
        }
    }

    public void trash() {
        getViewState().trash();
    }

    public void addShape(ShapeType shapeType, ShapesList shapesList) {
        IShape shape = mShapeFactory.createDefaultShape(shapeType);
        getViewState().addShape(shape);

        AddShapeCommand addShapeCommand = new AddShapeCommand(shapesList, shapeType, mShapeFactory);
        addShapeCommand.execute();
        CommandStack.getInstance().add(addShapeCommand);
    }
}
