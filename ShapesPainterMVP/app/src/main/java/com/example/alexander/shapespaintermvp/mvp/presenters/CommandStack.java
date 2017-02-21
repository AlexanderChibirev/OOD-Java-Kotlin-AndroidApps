package com.example.alexander.shapespaintermvp.mvp.presenters;

import com.example.alexander.shapespaintermvp.mvp.presenters.commands.ICommand;

import java.util.ArrayList;
import java.util.List;

class CommandStack {
    private static volatile CommandStack instance;

    private List<ICommand> mCommands = new ArrayList<>();
    private int mCurrentLocation = -1;

    public static CommandStack getInstance() {
        CommandStack localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandStack.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandStack();
                }
            }
        }
        return localInstance;
    }

    public void add(ICommand command) {
        clearInFrontOfCurrent();
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