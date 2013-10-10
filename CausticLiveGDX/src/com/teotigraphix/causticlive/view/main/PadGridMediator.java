
package com.teotigraphix.causticlive.view.main;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teotigraphix.causticlive.view.main.components.PadGrid;
import com.teotigraphix.causticlive.view.main.components.PadGrid.OnPadGridListener;
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

    //    @Inject
    //    ISequencerModel sequencerModel;

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
            }

            @Override
            public void onLongPress(Integer localIndex, float x, float y) {
            }

        });

        //
        //            @Override
        //            public void onLongPress(Integer localIndex, float x, float y) {
        //                sequencerModel.setActiveData(sequencerModel.getQueueData(
        //                        sequencerModel.getSelectedBank(), localIndex));
        //                screenProvider.getScreen().getGame().setScreen(ICausticLiveScreen.ASSIGN_SCREEN);
        //
        //                //                System.out.println("Long Press");
        //                //                final QueueData data = sequencerModel.getQueueData(
        //                //                        sequencerModel.getSelectedBank(), localIndex);
        //                //
        //                //                final List<LibraryPhrase> phrases = getController().getLibraryManager()
        //                //                        .getSelectedLibrary().getPhrases();
        //                //
        //                //                final Object[] items = new String[phrases.size()];
        //                //                for (int i = 0; i < items.length; i++) {
        //                //                    items[i] = phrases.get(i).toString();
        //                //                }
        //                //                final ListDialog alert = dialogManager.createListDialog(screenProvider.getScreen(),
        //                //                        "Choose Phrase", items);
        //                //                alert.setOnAlertDialogListener(new OnAlertDialogListener() {
        //                //                    @Override
        //                //                    public void onOk() {
        //                //                        System.out.println(items[alert.getSelectedIndex()]);
        //                //                        LibraryPhrase libraryPhrase = phrases.get(alert.getSelectedIndex());
        //                //                        sequencerModel.assignPhrase(data, libraryPhrase);
        //                //                    }
        //                //
        //                //                    @Override
        //                //                    public void onCancel() {
        //                //                    }
        //                //                });
        //                //                alert.show(screenProvider.getScreen().getStage());
        //            }
        //
        //            @Override
        //            public void onChange(int localIndex, boolean selected) {
        //                QueueData data = sequencerModel.getQueueData(sequencerModel.getSelectedBank(),
        //                        localIndex);
        //                sequencerModel.touch(data);
        //            }
        //        });
        //

        view.setPosition(640f, 89f);
        screen.getStage().addActor(view);
    }

    @Override
    public void onAttach(IScreen screen) {
        super.onAttach(screen);

        //        register(getController().getQueueSequencer(), OnQueueSequencerDataChange.class,
        //                new EventObserver<OnQueueSequencerDataChange>() {
        //                    @Override
        //                    public void trigger(OnQueueSequencerDataChange object) {
        //                        //updateView(sequencerModel.getViewData(sequencerModel.getSelectedBank()));
        //                    }
        //                });
        //
        //        register(sequencerModel, OnSequencerModelPropertyChange.class,
        //                new EventObserver<OnSequencerModelPropertyChange>() {
        //                    @Override
        //                    public void trigger(OnSequencerModelPropertyChange object) {
        //                        switch (object.getKind()) {
        //                            case Bank:
        //                                updateView(sequencerModel.getViewData(sequencerModel
        //                                        .getSelectedBank()));
        //                                break;
        //                            case ActiveData:
        //                                break;
        //                            default:
        //                                break;
        //                        }
        //                    }
        //                });
    }

    @Override
    public void onShow(IScreen screen) {
        //        updateView(sequencerModel.getViewData(sequencerModel.getSelectedBank()));
    }

    protected void updateView(Collection<QueueData> viewData) {
        //        view.refresh(viewData, true);
    }

}
