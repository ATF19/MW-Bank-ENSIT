import React from "react";
import { Switch } from "react-router";
import { Redirect, Route } from "react-router-dom";
import Account from "../page/account";
import Transfer from "../page/transfer";

const Routes = () => (
    <Switch>
        <Route path="/account" exact component={Account} />
        <Route path="/transfer" exact component={Transfer} />
        <Redirect to="/account" />
    </Switch>
)

export default Routes;