package com.example.alexander.shapespainter.controller;

import com.example.alexander.shapespainter.controller.commands.ICommand;

import java.util.ArrayList;
import java.util.List;

public class CommandStack {
    private List<ICommand> mCommands = new ArrayList();
    private int mCurrentLocation = -1;
    private int mSaveLocation = mCurrentLocation;


    public void add(ICommand command) {
        clearInFrontOfCurrent();
        command.execute();
        mCommands.add(command);
        mCurrentLocation++;
    }

    public void undo() {
        mCommands.get(mCurrentLocation).unExecute();
        mCurrentLocation--;
    }

    public boolean undoEnabled() {
        return mCurrentLocation >= 0;
    }

    public void redo() {
        mCurrentLocation++;
        mCommands.get(mCurrentLocation).execute();
    }

    public boolean redoEnabled() {
        return mCurrentLocation < mCommands.size() - 1;
    }

    public boolean dirty() {
        return mCurrentLocation != mSaveLocation;
    }

    private void clearInFrontOfCurrent() {
        while (mCurrentLocation < mCommands.size() - 1) {
            mCommands.remove(mCurrentLocation + 1);
        }
    }

    public void markSaveLocation() {
        mSaveLocation = mCurrentLocation;
    }

    @Override
    public String toString() {
        return mCommands.toString();
    }

}