const express = require('express');
const { exec } = require('child_process');
const path = require('path');

const app = express();
const port = 3000;

let recordingProcess;
let outputFilePath = '';

// Start recording
app.post('/start', (req, res) => {
  outputFilePath = `recording-${Date.now()}.mp4`;
  const command = `ffmpeg -f gdigrab -framerate 25 -i desktop ${outputFilePath}`;
  
  recordingProcess = exec(command, (error, stdout, stderr) => {
    if (error) {
      console.error(`Error: ${error.message}`);
    }
    if (stderr) {
      console.error(`Stderr: ${stderr}`);
    }
    console.log(`Stdout: ${stdout}`);
  });

  res.send('Recording started');
});

// Stop recording
app.post('/stop', (req, res) => {
  if (recordingProcess) {
    recordingProcess.kill('SIGINT');
    recordingProcess = null;
    res.send({ message: 'Recording stopped', url: `http://localhost:${port}/videos/${path.basename(outputFilePath)}` });
  } else {
    res.status(400).send('No recording in progress');
  }
});

// Serve recorded video
app.get('/videos/:filename', (req, res) => {
  const filePath = path.join(__dirname, req.params.filename);
  res.sendFile(filePath);
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
