import { Layout, Menu } from "antd";
import { Content } from "antd/lib/layout/layout";
import React from "react";
import { BrowserRouter, Link } from 'react-router-dom';
import Routes from "./routes";

const Main = () => {

    let selectedMenuElement = window.location.pathname.indexOf("transfer") > -1 ? "transfer" : "account";

    return (
        <BrowserRouter>
            <Layout>
                <Menu
                    theme="light"
                    mode="horizontal"
                    style={{ lineHeight: '64px' }}
                    defaultSelectedKeys={[selectedMenuElement]}
                    >
                        <Menu.Item key="logo" className="logo-container">
                            <h3>MaibornWolff Bank - ENSIT</h3>
                        </Menu.Item>
                        <Menu.Item key="account">
                            <Link to="/account">
                                Account
                            </Link>
                        </Menu.Item>
                        <Menu.Item key="transfer">
                            <Link to="/transfer">
                                Transfer
                            </Link>
                        </Menu.Item>
                </Menu>
                <Content>
                    {<Routes />}
                </Content>
            </Layout>
        </BrowserRouter>
    )

}

export default Main;