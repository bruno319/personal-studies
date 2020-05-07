import {Customer, CustomerService, Failure} from '../index'
import * as Fs from "fs";

class CustomerServiceImpl implements CustomerService {

    constructor(private filepath: string) {
    }

    public findAll = (): Promise<Customer[] | Failure> => {
        return Fs.promises.readFile(this.filepath)
            .then(data => {
                try {
                    return this.createCustomers(data);
                } catch (e) {
                    return {
                        message: e
                    } as Failure;
                }
            })
    };

    private createCustomers = (data: Buffer): Customer[] => {
        return data.toString()
            .split(/\r\n|\r|\n/)
            .map(line => line.split(/;/))
            .map(data => {
                let customer: Customer = {id: parseInt(data[0]), name: data[1]};
                return customer;
            })
            .filter(customer => customer.id)
    };

}

export default CustomerServiceImpl;