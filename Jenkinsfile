pipeline {
    // Chỉ định pipeline này CHỈ được chạy trên Agent có label là 'spring-boot'
    agent {
        label 'spring-boot' 
    }

    // Các biến môi trường dùng chung trong toàn bộ pipeline
    environment {
        APP_NAME = 'iot-telemetry-backend'
        IMAGE_TAG = 'v1'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Đang tải mã nguồn mới nhất từ kho lưu trữ...'
                // Jenkins tự động checkout code từ branch kích hoạt job
                checkout scm
            }
        }

        stage('Build Application') {
            steps {
                echo 'Đang biên dịch mã nguồn Spring Boot...'
                // Cấp quyền thực thi cho Maven Wrapper và tiến hành build (bỏ qua test để build nhanh)
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo 'Đang thực thi các bài kiểm thử tự động...'
                // Chạy Unit Test
                sh './mvnw test'
            }
            // Thu thập báo cáo kết quả test (nếu có)
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Đang xây dựng Docker image cho ứng dụng...'
                // Xây dựng Docker image với tag cụ thể
                sh "docker build -t ${APP_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Deploy Application') {
            steps {
                echo 'Đang triển khai hệ thống bằng Docker Compose...'
                sh 'docker compose down' // Dừng các container cũ nếu có
                sh 'docker compose up -d' // Triển khai container mới ở chế độ detached
            }
        }
    }

    // Các hành động sau khi pipeline chạy xong
    post {
        success {
            echo '🎉 Pipeline hoàn tất thành công! Ứng dụng đã sẵn sàng để đóng gói.'
        }
        failure {
            echo '❌ Pipeline thất bại. Vui lòng kiểm tra lại log của Jenkins.'
        }
    }
}