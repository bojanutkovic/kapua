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
package org.eclipse.kapua.app.console.client.account;

import org.eclipse.kapua.app.console.client.resources.icons.IconSet;
import org.eclipse.kapua.app.console.client.resources.icons.KapuaIcon;
import org.eclipse.kapua.app.console.client.ui.tab.KapuaTabItem;
import org.eclipse.kapua.app.console.shared.model.GwtSession;
import org.eclipse.kapua.app.console.shared.model.account.GwtAccount;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public class AccountTabConfiguration extends KapuaTabItem<GwtAccount> {

    private AccountConfigComponents configComponents;
    private AccountDetailsView accountDetailsView;
    private AccountDetailsTabDescription accountDetailsTabDescription;

    public AccountTabConfiguration(GwtSession currentSession, AccountDetailsView accoountDetailsView) {
        super("Settings", new KapuaIcon(IconSet.COG));
        this.accountDetailsView = accoountDetailsView;
        configComponents = new AccountConfigComponents(currentSession, this);
        setBorders(false);
        setLayout(new FitLayout());
        addListener(Events.Select, new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                refresh();
            }
        });
    }

    @Override
    public void setEntity(GwtAccount selectedAccount) {
        super.setEntity(selectedAccount);
        configComponents.setAccount(selectedAccount);
    }

    @Override
    protected void doRefresh() {
        configComponents.refresh();
    }

    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        setId("AccountTabsContainer");
        setLayout(new FitLayout());

        add(configComponents);
    }
    
    public void setDescriptionTab(AccountDetailsTabDescription accountDetailsTabDescription) {
    	this.accountDetailsTabDescription = accountDetailsTabDescription;
         configComponents.setDescriptionTab(accountDetailsTabDescription);

    }
   
}
