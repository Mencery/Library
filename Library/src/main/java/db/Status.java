package db;


import db.entity.User;

public enum Status {
    ADMIN, LIBRARIAN, READER;

    public static Status getStatus(User user) {
        int statusId = -1;
        switch (user.getStatus()) {
            case "admin":
                statusId = 0;
                break;
            case "librarian":
                statusId = 1;
                break;
            case "reader":
                statusId = 2;
                break;

        }
        return Status.values()[statusId];

    }

    public String getName() {
        return name().toLowerCase();
    }
}
