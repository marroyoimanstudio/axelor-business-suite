/**
 * Axelor Business Solutions
 *
 * Copyright (C) 2017 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.account.web;

import com.axelor.apps.account.db.ChequeRejection;
import com.axelor.apps.account.db.repo.ChequeRejectionRepository;
import com.axelor.apps.account.service.ChequeRejectionService;
import com.axelor.exception.service.TraceBackService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class ChequeRejectionController {

	@Inject
	private ChequeRejectionService crs;
	
	@Inject
	private ChequeRejectionRepository chequeRejectionRepo;
	
	public void validateChequeRejection(ActionRequest request, ActionResponse response)  {
		
		ChequeRejection chequeRejection = request.getContext().asType(ChequeRejection.class);
		chequeRejection = chequeRejectionRepo.find(chequeRejection.getId());
		
		try {
			crs.validateChequeRejection(chequeRejection);
			response.setReload(true);
		}
		catch(Exception e)  { TraceBackService.trace(response, e); }		
	}
}
