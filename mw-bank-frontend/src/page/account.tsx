import { UserAddOutlined } from '@ant-design/icons';
import { Alert, Button, Divider, Form, Input, Layout, message, Modal, Table } from 'antd';
import { Content } from 'antd/lib/layout/layout';
import React, { useCallback, useEffect, useState } from 'react';
import ApiConfig from '../apiConfig';
import { AccountDto, AccountRestServiceApi, CreateAccountDto } from '../service';

const Account = () => {
    const [loading, toggleLoading] = useState(false);
    const [accounts, setAccounts] = useState<AccountDto[]>([])
    const [showAddModal, toggleAddModal] = useState(false);
    const [addFirstName, changeAddFirstName] = useState("");
    const [addLastName, changeAddLastName] = useState("");
    const [addBalance, changeAddBalance] = useState(0);
    const [addBtnLoading, toggleAddBtnLoading] = useState(false)
    const [hasAddError, toggleAddError] = useState(false);
    const [addErrorMsg, changeAddErrorMsg] = useState("");

    const loadAccounts = useCallback(() => {
        toggleLoading(true);
        const accountApi = new AccountRestServiceApi(ApiConfig());
        accountApi.allAccounts()
            .then(response => {
                setAccounts(response.data);
            })
            .catch(err => message.error("An error occured. Please try again."))
            .finally(() => toggleLoading(false))
    }, [toggleLoading, setAccounts])

    useEffect(() => { loadAccounts() }, [loadAccounts])

    const addAccount = () => {
        toggleAddError(false);

        if(!addFirstName || addFirstName.length <= 0) {
            toggleAddError(true);
            changeAddErrorMsg("Please enter a first name.");
            return
        }

        if(!addLastName || addLastName.length <= 0) {
            toggleAddError(true);
            changeAddErrorMsg("Please enter a last name.");
            return
        }

        if(!addBalance) {
            toggleAddError(true);
            changeAddErrorMsg("Please enter a balance.");
            return
        }

        const request: CreateAccountDto = {
            firstName: addFirstName,
            lastName: addLastName,
            balance: addBalance
        };
        toggleAddBtnLoading(true)
        const accountApi = new AccountRestServiceApi(ApiConfig());
        accountApi.createAccount(request)
        .then(() => {
            closeAddModal();
            loadAccounts();
            message.success("The account was created successfully.");
        })
        .catch((error) => {
            if (error?.response?.data)
                changeAddErrorMsg(error?.response?.data);
            else
                changeAddErrorMsg("Could not create the account. Please try again.");
            toggleAddError(true);
        })
        .finally(() => toggleAddBtnLoading(false))
    }

    const closeAddModal = () => {
        changeAddFirstName("");
        changeAddLastName("");
        changeAddBalance(0);
        toggleAddError(false);
        toggleAddBtnLoading(false);
        toggleAddModal(false);
    } 

    const columns = [
        {
            title: "First Name",
            dataIndex: 'firstName'
        },
        {
            title: "Last Name",
            dataIndex: 'lastName'
        },
        {
            title: "Balance",
            dataIndex: 'balance'
        }
    ]

    return (
        <Content className="content">
            <div className="header-with-button-on-right">
                <h2>Account</h2>
                <Button size='large' icon={<UserAddOutlined />} onClick={() => toggleAddModal(true)}>Create</Button>
            </div>
            <Divider plain />
            <Table
                columns={columns}
                rowKey={(row: AccountDto) => row.id!!}
                dataSource={accounts}
                loading={loading}
                locale={{ emptyText: "No accounts." }}
            />

            <Modal title="Create an account" visible={showAddModal} width={800} onCancel={closeAddModal} footer={null}>
                <Form
                    labelCol={{ span: 5 }}
                    wrapperCol={{ span: 18 }}
                    layout="horizontal"
                    size="middle"
                    onFinish={addAccount}
                    labelAlign="left">
                        <Form.Item label="First Name" rules={[{ required: true, message: "Please enter a first name.", whitespace: true }]}>
                            <Input placeholder="First Name..." value={addFirstName} onChange={(val: any) => changeAddFirstName(val.target.value)} />
                        </Form.Item>
                        <Form.Item label="Last Name" rules={[{ required: true, message: "Please enter a last name.", whitespace: true }]}>
                            <Input placeholder="Last Name..." value={addLastName} onChange={(val: any) => changeAddLastName(val.target.value)} />
                        </Form.Item>
                        <Form.Item label="Balance" rules={[{ required: true, message: "Please enter a balance.", whitespace: true }]}>
                            <Input placeholder="Balance..." value={addBalance} onChange={(val: any) => changeAddBalance(val.target.value)} />
                        </Form.Item>

                        {hasAddError ? (
                            <Form.Item wrapperCol={{ offset: 5, span: 18 }}>
                                <Alert
                                    message={addErrorMsg}
                                    type="error"
                                    closable
                                    afterClose={() => toggleAddError(false)}
                                />
                            </Form.Item>
                        ) : null}
                        <Form.Item wrapperCol={{ offset: 5, span: 18 }}>
                            <Button block type="primary" htmlType="submit" loading={addBtnLoading}>
                                Create
                            </Button>
                        </Form.Item>
                </Form>
            </Modal>
        </Content>
    ) 
}

export default Account;