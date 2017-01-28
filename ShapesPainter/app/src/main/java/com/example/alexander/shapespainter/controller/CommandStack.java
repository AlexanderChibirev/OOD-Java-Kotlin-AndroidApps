package com.example.alexander.shapespainter.controller;

import java.util.ArrayList;
import java.util.List;

class CommandStack {
    private List<ICommand> mCommands = new ArrayList();
    private int mCurrentLocation = -1;

    public void add(ICommand command) {
        clearInFrontOfCurrent();
        command.execute();
        mCommands.add(command);
        mCurrentLocation++;
    }

    void undo() {
        mCommands.get(mCurrentLocation).unExecute();
        mCurrentLocation--;
    }

    boolean undoEnabled() {
        return mCurrentLocation >= 0;
    }

    void redo() {
        mCurrentLocation++;
        mCommands.get(mCurrentLocation).execute();
    }

    boolean redoEnabled() {
        return mCurrentLocation < mCommands.size() - 1;
    }

    private void clearInFrontOfCurrent() {
        while (mCurrentLocation < mCommands.size() - 1) {
            mCommands.remove(mCurrentLocation + 1);
        }
    }
}