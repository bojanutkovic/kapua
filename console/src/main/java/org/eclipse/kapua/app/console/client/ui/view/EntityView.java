/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.console.client.ui.view;

import java.util.List;

import org.eclipse.kapua.app.console.client.ui.grid.EntityGrid;
import org.eclipse.kapua.app.console.client.ui.panel.EntityFilterPanel;
import org.eclipse.kapua.app.console.client.ui.panel.KapuaBorderLayoutData;
import org.eclipse.kapua.app.console.client.ui.panel.KapuaTabPanel;
import org.eclipse.kapua.app.console.client.ui.tab.KapuaTabItem;
import org.eclipse.kapua.app.console.shared.model.GwtEntityModel;
import org.eclipse.kapua.app.console.shared.model.GwtSession;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public abstract class EntityView<M extends GwtEntityModel> extends LayoutContainer {

    private GwtSession currentSession;

    private EntityFilterPanel<M> filterPanel;
    private EntityGrid<M> entityGrid;
    private KapuaTabPanel<M> tabsPanel;

    public EntityView() {
        super();
    }

    public EntityView(GwtSession currentSession) {
        this();
        this.currentSession = currentSession;
    }

    public void setSelectedEntity(M entity) {
        tabsPanel.setEntity(entity);
    }

    protected void onRender(final Element parent, int index) {

        super.onRender(parent, index);

        setLayout(new FitLayout());
        setBorders(false);

        LayoutContainer mf = new LayoutContainer();
        mf.setBorders(false);
        mf.setLayout(new BorderLayout());

        BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST, 220);
        eastData.setMargins(new Margins(0, 0, 0, 0));
        eastData.setCollapsible(false);
        eastData.setSplit(false);
        filterPanel = getEntityFilterPanel(this, currentSession);
        ContentPanel panel = new ContentPanel();
        panel.setLayout(new FitLayout());
        panel.setBorders(false);
        panel.setBodyBorder(false);
        //
        // East Panel: Filtering menu

        if (filterPanel != null) {
            panel.add(filterPanel);
            panel.setHeaderVisible(hidden);
        }

        //
        // Center Main panel:
        BorderLayoutData centerMainPanel = new BorderLayoutData(LayoutRegion.CENTER);
        centerMainPanel.setMargins(new Margins(0, 5, 0, 0));
        centerMainPanel.setSplit(false);

        LayoutContainer resultContainer = new LayoutContainer(new BorderLayout());
        resultContainer.setBorders(false);

        mf.add(filterPanel, eastData);
        mf.add(resultContainer, centerMainPanel);

        //
        // North sub panel: Entity grid
        entityGrid = getEntityGrid(this, currentSession);

        if (filterPanel != null) {
            filterPanel.setEntityGrid(entityGrid);
            entityGrid.setFilterPanel(filterPanel);
        }

        BorderLayoutData northData = new KapuaBorderLayoutData(LayoutRegion.NORTH, .45F);
        resultContainer.add(entityGrid, northData);

        //
        // Center sub panel: Entity sub tabs
        tabsPanel = new KapuaTabPanel<M>();
        tabsPanel.setStyleAttribute("padding-top", "5px");

        List<KapuaTabItem<M>> tabItems = getTabs(this, currentSession);

        for (KapuaTabItem<M> kti : tabItems) {
            tabsPanel.add(kti);
        }

        KapuaBorderLayoutData centerData = new KapuaBorderLayoutData(LayoutRegion.CENTER, .55F);
        centerData.setMarginTop(5);

        resultContainer.add(tabsPanel, centerData);

        add(mf);
    }

    public abstract List<KapuaTabItem<M>> getTabs(EntityView<M> entityView, GwtSession currentSession);

    public abstract EntityGrid<M> getEntityGrid(EntityView<M> entityView, GwtSession currentSession);

    public abstract EntityFilterPanel<M> getEntityFilterPanel(EntityView<M> entityView, GwtSession currentSession2);

    public void onUnload() {
        super.onUnload();
    }

}