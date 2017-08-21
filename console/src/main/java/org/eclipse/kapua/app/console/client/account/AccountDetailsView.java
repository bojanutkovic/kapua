/*******************************************************************************
 * Copyright (c) 2011, 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.console.client.account;

import org.eclipse.kapua.app.console.shared.model.GwtSession;
import org.eclipse.kapua.app.console.shared.model.account.GwtAccount;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public class AccountDetailsView extends LayoutContainer {

    private GwtSession currentSession;
    private AccountView centerAccountView;

    private GwtAccount selectedAccount;


    public AccountDetailsView(AccountView centerAccountView, GwtSession currentSession) {
        this.centerAccountView = centerAccountView;
        this.currentSession = currentSession;

    }

    public void setAccount(GwtAccount selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new FitLayout());
        setBorders(false);

        LayoutContainer bodyLayoutContainer = new LayoutContainer();
        bodyLayoutContainer.setBorders(true);
        bodyLayoutContainer.setLayout(new BorderLayout());
        bodyLayoutContainer.setScrollMode(Scroll.AUTO);
        bodyLayoutContainer.setStyleAttribute("background-color", "#F0F0F0");
        bodyLayoutContainer.setStyleAttribute("padding", "0px");
        BorderLayoutData centerMainPanel = new BorderLayoutData(LayoutRegion.CENTER);
        centerMainPanel.setMargins(new Margins(0, 5, 0, 0));
        centerMainPanel.setSplit(false);

        TabPanel tabPanel = new TabPanel();
        LayoutContainer resultContainer = new LayoutContainer(new BorderLayout());
        resultContainer.setBorders(false);

        bodyLayoutContainer.add(resultContainer, centerMainPanel);
        BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, .45F);
        northData.setMargins(new Margins(0, 0, 0, 0));
        northData.setSplit(true);
        northData.setMinSize(0);
        resultContainer.add(tabPanel, northData);

        tabPanel.setPlain(true);
        tabPanel.setBorders(false);
        tabPanel.setBodyBorder(false);
        AccountTabConfiguration settingsTabItem = new AccountTabConfiguration(currentSession, this);
        settingsTabItem.setEntity(selectedAccount);
        tabPanel.add(settingsTabItem);
        AccountDetailsTabDescription accountDescriptionTab = new AccountDetailsTabDescription(currentSession, this, centerAccountView);
        settingsTabItem.setDescriptionTab(accountDescriptionTab);
        accountDescriptionTab.setAccount(selectedAccount);
        accountDescriptionTab.initTable();
        tabPanel.add(accountDescriptionTab);

        bodyLayoutContainer.add(tabPanel, northData);

        add(bodyLayoutContainer);

    }

}
