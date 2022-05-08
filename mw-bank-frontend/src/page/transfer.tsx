import { SendOutlined, UserAddOutlined } from '@ant-design/icons';
import { Alert, Button, Divider, Form, Input, message, Modal, Select, Table } from 'antd';
import { Content } from 'antd/lib/layout/layout';
import React, { useCallback, useEffect, useState } from 'react';
import ApiConfig from '../apiConfig';
import { AccountDto, AccountRestServiceApi, CreateTransferDto, TransferDto, TransferRestServiceApi } from '../service';

const Transfer = () => {
    const [loading, toggleLoading] = useState(false);
    const [transfers, setTransfers] = useState<TransferDto[]>([])
    const [accounts, setAccounts] = useState<AccountDto[]>([])
    const [showAddModal, toggleAddModal] = useState(false);
    const [addSource, changeAddSource] = useState("");
    const [addDestination, changeAddDestination] = useState("");
    const [addAmount, changeAddAmount] = useState(0);
    const [addBtnLoading, toggleAddBtnLoading] = useState(false)
    const [hasAddError, toggleAddError] = useState(false);
    const [addErrorMsg, changeAddErrorMsg] = useState("");

    const loadTransfers = useCallback(() => {
        toggleLoading(true);
        const transfersApi = new TransferRestServiceApi(ApiConfig());
        transfersApi.allTransfers()
            .then(response => {
                setTransfers(response.data);
            })
            .catch(err => message.error("An error occured. Please try again."))
            .finally(() => toggleLoading(false))
    }, [toggleLoading, setTransfers])


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
    
    useEffect(() => { loadTransfers() }, [loadTransfers])
    useEffect(() => { loadAccounts() }, [loadAccounts])

    const addTransfer = () => {
        toggleAddError(false);

        if(!addSource || addSource.length <= 0) {
            toggleAddError(true);
            changeAddErrorMsg("Please choose a source account.");
            return
        }

        if(!addDestination || addDestination.length <= 0) {
            toggleAddError(true);
            changeAddErrorMsg("Please choose a destination account.");
            return
        }

        if(!addAmount) {
            toggleAddError(true);
            changeAddErrorMsg("Please enter an amount.");
            return
        }

        const request: CreateTransferDto = {
            toAccountId: addDestination,
            fromAccountId: addSource,
            amount: addAmount
        };
        toggleAddBtnLoading(true)
        const transfersApi = new TransferRestServiceApi(ApiConfig());
        transfersApi.createTransfer(request)
        .then(() => {
            closeAddModal();
            loadTransfers();
            message.success("The transfer was created successfully.");
        })
        .catch((error) => {
            if (error?.response?.data)
                changeAddErrorMsg(error?.response?.data);
            else
                changeAddErrorMsg("Could not create the transfer. Please try again.");
            toggleAddError(true);
        })
        .finally(() => toggleAddBtnLoading(false))
    }

    const closeAddModal = () => {
        changeAddSource("");
        changeAddDestination("");
        changeAddAmount(0);
        toggleAddError(false);
        toggleAddBtnLoading(false);
        toggleAddModal(false);
    } 

    const columns = [
        {
            title: "From",
            render: (text: string, record: TransferDto) => record.from?.firstName + " " + record.from?.lastName
        },
        {
            title: "To",
            render: (text: string, record: TransferDto) => record.to?.firstName + " " + record.to?.lastName
        },
        {
            title: "Amount",
            dataIndex: 'amount'
        }
    ]

    const renderAccounts = () => {
        return accounts.map(acc => {
            return <Select.Option key={acc.id} value={acc.id}>{acc.firstName + " " + acc.lastName}</Select.Option>;
        });
    }

    return (
        <Content className="content">
            <div className="header-with-button-on-right">
                <h2>Transfers</h2>
                <Button size='large' icon={<SendOutlined />} onClick={() => toggleAddModal(true)}>Create</Button>
            </div>
            <Divider plain />
            <Table
                columns={columns}
                rowKey={(row: TransferDto) => row.id!!}
                dataSource={transfers}
                loading={loading}
                locale={{ emptyText: "No transfers." }}
            />

            <Modal title="Create ana transfer" visible={showAddModal} width={800} onCancel={closeAddModal} footer={null}>
                <Form
                    labelCol={{ span: 5 }}
                    wrapperCol={{ span: 18 }}
                    layout="horizontal"
                    size="middle"
                    onFinish={addTransfer}
                    labelAlign="left">

                        <Form.Item label="From" rules={[{ required: true, message: "Please enter a source account.", whitespace: true }]}>
                            <Select onChange={(val: any) => changeAddSource(val)}>
                                {renderAccounts()}
                            </Select>
                        </Form.Item>

                        <Form.Item label="To" rules={[{ required: true, message: "Please enter a destination account.", whitespace: true }]}>
                            <Select onChange={(val: any) => changeAddDestination(val)}>
                                {renderAccounts()}
                            </Select>
                        </Form.Item>

                        <Form.Item label="Amount" rules={[{ required: true, message: "Please enter an amount.", whitespace: true }]}>
                            <Input placeholder="Amount..." value={addAmount} onChange={(val: any) => changeAddAmount(val.target.value)} />
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

export default Transfer;