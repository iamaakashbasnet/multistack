import express from 'express';
import notesRouter from './routes/notes';

(BigInt.prototype as any).toJSON = function () {
  return this.toString();
};

const app = express();

app.use(express.json());
app.use('/', notesRouter);

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`🚀 Server running on http://localhost:${PORT}`);
});
