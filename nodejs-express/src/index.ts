import Express, {Request, Response, Router} from "express";
import CustomerControllerImpl from "./controller/customer.controller";
import CustomerServiceImpl from "./service/customer.service";

export interface Customer {
    id: number;
    name: string;
}

export interface Failure {
    message: string;
}

export interface CustomerService {
    findAll(): Promise<Customer[] | Failure>;
}

export interface CustomerController {
    findAll(request: Request, response: Response): Promise<unknown>;
}

const router: Router = Router();
const service: CustomerService = new CustomerServiceImpl('resources/customers.csv');
const customerController: CustomerController = new CustomerControllerImpl(service, router);

Express()
    .use(Express.json())
    .use(router)
    .listen(3000);


