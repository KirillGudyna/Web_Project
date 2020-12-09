package com.gudyna.webproject.controller.impl;

import com.google.gson.Gson;
import com.gudyna.webproject.controller.ActionCommand;
import com.gudyna.webproject.controller.PageName;
import com.gudyna.webproject.controller.Router;
import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.model.service.PurposeService;
import com.gudyna.webproject.model.service.impl.PurposeServiceImpl;
import com.gudyna.webproject.request.form.RequestPurposeAddData;
import com.gudyna.webproject.response.ResponseWrapper;
import com.gudyna.webproject.response.form.ResponsePurposeData;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddPurposeCommand implements ActionCommand {
    private final PurposeService purposeService = PurposeServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        Router router;
        ResponsePurposeData data = gson.fromJson(request.getReader().lines().filter(line -> line.startsWith("{") && line.endsWith("}")).collect(Collectors.joining()), ResponsePurposeData.class);
        try {
            data = purposeService.addPurpose(new RequestPurposeAddData());
            request.setAttribute("response", ResponseWrapper.setSuccess("Purpose successfully added", data));
            router = new Router("before add purpose page");
        } catch (ServiceException e) {
            request.setAttribute("response", ResponseWrapper.setError("Unable to add purpose" + e.getMessage()));
            router = new Router(PageName.ERROR.getPath());
        }
        return router;
    }
}
