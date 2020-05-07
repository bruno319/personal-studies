import {Request, Response, Router} from "express";
import {CustomerController, CustomerService} from "../index"

class CustomerControllerImpl implements CustomerController {
    public basePath: string = "/customers";

    constructor(private customerService: CustomerService, private router: Router) {
        this.initRoutes();
    }

    private initRoutes() {
        this.router.get(this.basePath, this.findAll)
    }

    public findAll = (request: Request, response: Response): Promise<unknown> => {
        return this.customerService
            .findAll()
            .then(data => response.status(200).send(data))
            .catch(err => response.status(500).send(err));
    }
}

export default CustomerControllerImpl;