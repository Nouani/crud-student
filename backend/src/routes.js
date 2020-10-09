import { Router } from 'express';

import Student from './app/models/Student';

const routes = new Router();

routes.get('/', async (req, res) => {
    const student = await Student.create({
        RA: '19194',
        name: 'nametest',
        email: 'name@gmail.com',
    });

    return res.json(student);
});

export default routes;
