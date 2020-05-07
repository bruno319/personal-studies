import {Universe, Cell} from "wasm-game-of-life";
import { memory } from "wasm-game-of-life/wasm_game_of_life_bg";

const CELL_SIZE = 5;
const GRID_COLOR = "#CCC";
const DEAD_COLOR = "#FFF";
const ALIVE_COLOR = "#000";

const universe = Universe.new();
const canvas = document.getElementById("game-of-life-canvas");
const playPauseButton = document.getElementById("play-pause");

canvas.height = (CELL_SIZE + 1) * universe.height() + 1;
canvas.width = (CELL_SIZE + 1) * universe.width() + 1;

const ctx = canvas.getContext('2d');

let animationId = null;

const renderLoop = () => {
    drawGrid();
    drawCells();

    universe.tick();

    animationId = requestAnimationFrame(renderLoop);
};

const getIndex = (row, column) => {
    return row * universe.width() + column;
};

const isPaused = () => {
    return animationId === null;
};

const drawGrid = () => {
    ctx.beginPath();
    ctx.strokeStyle = GRID_COLOR;

    // Vertical lines.
    for (let i = 0; i <= universe.width(); i++) {
        ctx.moveTo(i * (CELL_SIZE + 1) + 1, 0);
        ctx.lineTo(i * (CELL_SIZE + 1) + 1, (CELL_SIZE + 1) * universe.height() + 1);
    }

    // Horizontal lines.
    for (let j = 0; j <= universe.height(); j++) {
        ctx.moveTo(0,                           j * (CELL_SIZE + 1) + 1);
        ctx.lineTo((CELL_SIZE + 1) * universe.width() + 1, j * (CELL_SIZE + 1) + 1);
    }

    ctx.stroke();
};

const drawCells = () => {
    const cellsPtr = universe.cells();
    const cells = new Uint8Array(memory.buffer, cellsPtr, universe.width() * universe.height());

    ctx.beginPath();

    for (let row = 0; row < universe.height(); row++) {
        for (let col = 0; col < universe.width(); col++) {
            const idx = getIndex(row, col);

            ctx.fillStyle = cells[idx] === Cell.Dead
                ? ALIVE_COLOR
                : DEAD_COLOR;

            ctx.fillRect(
                col * (CELL_SIZE + 1) + 1,
                row * (CELL_SIZE + 1) + 1,
                CELL_SIZE,
                CELL_SIZE
            );
        }
    }

    ctx.stroke();
};

const play = () => {
    playPauseButton.textContent = "⏸";
    renderLoop();
};

const pause = () => {
    playPauseButton.textContent = "▶";
    cancelAnimationFrame(animationId);
    animationId = null;
};

playPauseButton.addEventListener("click", ev => {
   if (isPaused()) {
       play();
   } else {
       pause();
   }
});

canvas.addEventListener("click", ev => {
    const boundingRect = canvas.getBoundingClientRect();

    const scaleX = canvas.width / boundingRect.width;
    const scaleY = canvas.height / boundingRect.height;

    const canvasLeft = (ev.clientX - boundingRect.left) * scaleX;
    const canvasTop = (ev.clientY - boundingRect.top) * scaleY;

    const row = Math.min(Math.floor(canvasTop / (CELL_SIZE + 1)), universe.height() - 1);
    const col = Math.min(Math.floor(canvasLeft / (CELL_SIZE + 1)), universe.width() - 1);

    universe.toggle_cell(row, col);

    drawGrid();
    drawCells();
});

drawGrid();
drawCells();
play();