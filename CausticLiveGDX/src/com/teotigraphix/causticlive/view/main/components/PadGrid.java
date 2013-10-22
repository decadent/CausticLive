////////////////////////////////////////////////////////////////////////////////
// Copyright 2013 Michael Schmalle - Teoti Graphix, LLC
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
// http://www.apache.org/licenses/LICENSE-2.0 
// 
// Unless required by applicable law or agreed to in writing, software 
// distributed under the License is distributed on an "AS IS" BASIS, 
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and 
// limitations under the License
// 
// Author: Michael Schmalle, Principal Architect
// mschmalle at teotigraphix dot com
////////////////////////////////////////////////////////////////////////////////

package com.teotigraphix.causticlive.view.main.components;

import java.util.Collection;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.teotigraphix.caustk.rack.queue.QueueData;
import com.teotigraphix.libgdx.ui.OverlayButton.OnPadButtonListener;

public class PadGrid extends WidgetGroup {

    //--------------------------------------------------------------------------
    // Public :: Variables
    //--------------------------------------------------------------------------

    public float padding;

    public float padSize;

    //--------------------------------------------------------------------------
    // Private :: Variables
    //--------------------------------------------------------------------------

    private Skin skin;

    private int numRows = 4;

    private int numColums = 4;

    private boolean sizeInvalid;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public PadGrid(Skin skin) {
        this.skin = skin;
        createChildren();
    }

    private void createChildren() {
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColums; j++) {
                final PadButton button = createButton(index);
                addActor(button);
                index++;
            }
        }
    }

    private PadButton createButton(int index) {
        final PadButton button = new PadButton("", skin);
        button.setIndex(index);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PadButton button = (PadButton)actor;
                listener.onChange(button.getIndex(), button.getData());
            }
        });
        button.setOnPadButtonListener(new OnPadButtonListener() {
            @Override
            public void onLongPress(Integer index, float x, float y) {
                listener.onLongPress(index, x, y);
            }
        });
        return button;
    }

    private void computeSize() {
    }

    //--------------------------------------------------------------------------
    // Overridden :: Methods
    //--------------------------------------------------------------------------

    @Override
    public void layout() {
        // called at the end of validate()

        if (sizeInvalid)
            computeSize();

        float gap = padding * 2;

        float calcX = padding;
        float calcY = padding;

        float calcWidth = padSize;
        float calcHeight = padSize;

        int index = 0;
        Array<Actor> children = getChildren();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColums; j++) {
                Actor child = children.get(index);
                child.setX(calcX);
                child.setY(calcY);
                child.setSize(calcWidth, calcHeight);
                calcX += gap + calcWidth;
                index++;
            }
            calcX = padding;
            calcY += gap + calcHeight;
        }
    }

    //--------------------------------------------------------------------------
    // Public API :: Methods
    //--------------------------------------------------------------------------

    public void refresh(Collection<QueueData> viewData, boolean selected) {
        final SnapshotArray<Actor> children = getChildren();
        for (Actor button : children) {
            ((PadButton)button).setData(null);
        }

        int index = 0;
        for (QueueData queueData : viewData) {
            if (queueData != null) {
                PadButton padButton = (PadButton)children.get(index);
                padButton.setData(queueData);
            }
            index++;
        }
    }

    public void updateActive(QueueData data, boolean show) {
        SnapshotArray<Actor> children = getChildren();
        for (Actor actor : children) {
            ((PadButton)actor).setActive(false);
        }
        if (data != null) {
            ((PadButton)children.get(data.getPatternIndex())).setActive(show);
        }
    }

    //--------------------------------------------------------------------------
    // Events
    //--------------------------------------------------------------------------

    private OnPadGridListener listener;

    public void setOnPadGridListener(OnPadGridListener l) {
        listener = l;
    }

    public interface OnPadGridListener {
        void onChange(int localIndex, QueueData data);

        void onLongPress(Integer localIndex, float x, float y);
    }
}
