import { Router } from 'express';

import StudentController from './app/controllers/StudentController';

const routes = new Router();

routes.get('/student', StudentController.index);
routes.get('/student/:RA', StudentController.show);
routes.post('/student', StudentController.store);

export default routes;
