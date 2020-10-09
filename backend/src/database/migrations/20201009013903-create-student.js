module.exports = {
    up: (queryInterface, Sequelize) => {
        return queryInterface.createTable('Students', {
            RA: {
                type: Sequelize.STRING,
                allowNull: false,
                primaryKey: true,
                autoIncrement: false,
            },
            name: {
                type: Sequelize.STRING,
                allowNull: false,
            },
            email: {
                type: Sequelize.STRING,
                allowNull: false,
                unique: true,
            },
            createdAt: {
                type: Sequelize.DATE,
                allowNull: false,
            },
            updatedAt: {
                type: Sequelize.DATE,
                allowNull: false,
            },
        });
    },

    down: queryInterface => {
        return queryInterface.dropTable('Students');
    },
};
