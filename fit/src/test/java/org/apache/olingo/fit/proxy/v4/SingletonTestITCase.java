/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.fit.proxy.v4;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.apache.olingo.fit.proxy.v4.staticservice.microsoft.test.odata.services.odatawcfservice.types.Company;
import org.apache.olingo.fit.proxy.v4.staticservice.microsoft.test.odata.services.odatawcfservice.types.CompanyCategory;
import org.apache.olingo.fit.proxy.v4.staticservice.microsoft.test.odata.services.odatawcfservice.types.IsBoss;
import org.apache.olingo.fit.proxy.v4.staticservice.microsoft.test.odata.services.odatawcfservice.types.Person;
import org.junit.Test;

public class SingletonTestITCase extends AbstractTestITCase {

  @Test
  public void read() {
    final Company company = container.getCompany().get();
    assertEquals(0, company.getCompanyID(), 0);
    assertEquals(CompanyCategory.IT, company.getCompanyCategory());
  }

  @Test
  public void update() {
    final Company company = container.getCompany().get();
    company.setRevenue(132520L);

    container.flush();

    assertEquals(132520L, container.getCompany().get().getRevenue(), 0);
  }

  @Test
  public void readWithAnnotations() {
    final Company company = container.getCompany().get();
    assertTrue(company.getAnnotationTerms().isEmpty());
    
    final Person boss = container.getBoss().get();
    assertEquals(2, boss.getPersonID(), 0);

    assertEquals(1, boss.getAnnotationTerms().size());
    final Object isBoss = boss.getAnnotation(IsBoss.class);
    assertTrue(isBoss instanceof Boolean);
    assertTrue((Boolean) isBoss);
  }
}