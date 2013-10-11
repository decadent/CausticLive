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

package com.teotigraphix.causticlive.view.main;

import java.util.Collection;

import org.androidtransfuse.event.EventObserver;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teotigraphix.causticlive.model.ISequencerModel;
import com.teotigraphix.causticlive.model.ISequencerModel.OnSequencerModelPropertyChange;
import com.teotigraphix.causticlive.screen.ICausticLiveScreen;
import com.teotigraphix.causticlive.view.main.components.PadGrid;
import com.teotigraphix.causticlive.view.main.components.PadGrid.OnPadGridListener;
import com.teotigraphix.caustk.sequencer.IQueueSequencer.OnQueueSequencerDataChange;
import com.teotigraphix.caustk.sequencer.queue.QueueData;
import com.teotigraphix.libgdx.controller.ScreenMediator;
import com.teotigraphix.libgdx.dialog.IDialogManager;
import com.teotigraphix.libgdx.scene2d.IScreenProvider;
import com.teotigraphix.libgdx.screen.IScreen;

@Singleton
public class PadGridMediator extends ScreenMediator {

    @Inject
    IDialogManager dialogManager;

    @Inject
    IScreenProvider screenProvider;

    @Inject
    ISequencerModel sequencerModel;

    private PadGrid view;

    public PadGridMediator() {
    }

    @Override
    public void onCreate(IScreen screen) {
        super.onCreate(screen);

        view = new PadGrid(screen.getSkin());
        view.setOnPadGridListener(new OnPadGridListener() {

            @Override
            public void onChange(int localIndex, boolean selected) {
                QueueData data = sequencerModel.getQueueData(sequencerModel.getSelectedBank(),
                        localIndex);
                if (data.getViewChannelIndex() != -1)
                    sequencerModel.touch(data);
            }

            @Override
            public void onLongPress(Integer localIndex, float x, float y) {
                getController().getLogger().log("PadGridMediator", "long press");
                sequencerModel.setActiveData(sequencerModel.getQueueData(
                        sequencerModel.getSelectedBank(), localIndex));
                screenProvider.getScreen().getGame().setScreen(ICausticLiveScreen.ASSIGN_SCREEN);
            }

        });

        view.setPosition(640f, 89f);
        screen.getStage().addActor(view);
    }

    @Override
    public void onAttach(IScreen screen) {
        super.onAttach(screen);

        register(getController(), OnQueueSequencerDataChange.class,
                new EventObserver<OnQueueSequencerDataChange>() {
                    @Override
                    public void trigger(OnQueueSequencerDataChange object) {
                        updateView(sequencerModel.getViewData(sequencerModel.getSelectedBank()));
                    }
                });

        register(sequencerModel, OnSequencerModelPropertyChange.class,
                new EventObserver<OnSequencerModelPropertyChange>() {
                    @Override
                    public void trigger(OnSequencerModelPropertyChange object) {
                        switch (object.getKind()) {
                            case Bank:
                                updateView(sequencerModel.getViewData(sequencerModel
                                        .getSelectedBank()));
                                break;
                            case ActiveData:
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    @Override
    public void onShow(IScreen screen) {
        updateView(sequencerModel.getViewData(sequencerModel.getSelectedBank()));
    }

    protected void updateView(Collection<QueueData> viewData) {
        view.refresh(viewData, true);
    }
}
