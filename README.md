# CloudGuard
A serverless AWS framework to automate cloud security tasks.

## Features
- Scans S3 buckets for public access
- Monitors IAM policies with CloudTrail
- Triggers Lambda alerts via CloudWatch

## Tech Stack
- AWS (Lambda, S3, CloudWatch, CloudTrail)
- Python 3.9 (boto3), Terraform

## Setup
1. Clone the repo: git clone https://github.com/ravitejaannam-008/CloudGuard.git cd CloudGuard
2. Install dependencies: pip install -r requirements.txt terraform init
3. Configure AWS CLI with credentials.
## Usage
Deploy the framework: terraform apply
Run a manual scan: python scan.py --bucket my-bucket
## Example
$ python scan.py --bucket my-bucket Found 2 public S3 objects—alert sent to CloudWatch

## Why This Matters
Based on my Indian Railways work, where I cut unauthorized access by 60%—now serverless and scalable.
