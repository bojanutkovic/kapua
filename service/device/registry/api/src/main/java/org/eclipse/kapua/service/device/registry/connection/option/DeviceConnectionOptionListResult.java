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
package org.eclipse.kapua.service.device.registry.connection.option;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.query.KapuaListResult;

/**
 * Device connection options list definition.
 *
 * @since 1.0.0
 */
@XmlRootElement(name = "deviceConnectionOptions")
@XmlType(factoryClass = DeviceConnectionOptionXmlRegistry.class, factoryMethod = "newDeviceConnectionOptionListResult")
public interface DeviceConnectionOptionListResult extends KapuaListResult<DeviceConnectionOption> {
}
