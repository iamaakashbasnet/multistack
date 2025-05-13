-- CreateTable
CREATE TABLE "todos" (
    "id" BIGSERIAL NOT NULL,
    "title" VARCHAR(255),
    "created_at" TIMESTAMPTZ(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "user_id" BIGINT,

    CONSTRAINT "todos_pkey" PRIMARY KEY ("id")
);
